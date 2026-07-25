#![allow(unused)]
use std::cmp::{max, min, Reverse};
use std::collections::{BTreeMap, BTreeSet, BinaryHeap, HashMap, HashSet, VecDeque};
use std::fs::File;
use std::io::{self, BufRead, BufReader, BufWriter, Read, Stdout, Write};
use std::str::FromStr;

fn main() {
  let reader = open_input();
  let writer = io::stdout();
  let mut io = Io::new(reader, writer);

  solve(&mut io);
  io.flush();
}

type Reader = Box<dyn Read>;

const N: usize = 8;
type ChessboardCell = (usize, usize);

fn count_queen_placements(
  row: usize,
  forbidden_cells: &HashSet<ChessboardCell>,
  busy_cols: &mut [bool; N],
  busy_diags_lr: &mut [bool; 2 * N],
  busy_diags_rl: &mut [bool; 2 * N],
  solutions: &mut u32,
) {
  if row == N {
    *solutions += 1;
    return;
  }
  for col in 0..N {
    if (forbidden_cells.contains(&(row, col))
      || busy_cols[col]
      || busy_diags_lr[row + col]
      || busy_diags_rl[row + N - col])
    {
      continue;
    }
    busy_cols[col] = true;
    busy_diags_lr[row + col] = true;
    busy_diags_rl[row + N - col] = true;
    count_queen_placements(
      row + 1,
      forbidden_cells,
      busy_cols,
      busy_diags_lr,
      busy_diags_rl,
      solutions,
    );
    busy_cols[col] = false;
    busy_diags_lr[row + col] = false;
    busy_diags_rl[row + N - col] = false;
  }
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let mut forbidden_cells: HashSet<ChessboardCell> = HashSet::new();
  for i in 0..N {
    for j in 0..N {
      if io.next_char().unwrap() == '*' {
        forbidden_cells.insert((i, j));
      }
    }
  }
  let mut solutions: u32 = 0;
  let mut busy_cols: [bool; N] = [false; N];
  let mut busy_diags_lr: [bool; 2 * N] = [false; 2 * N];
  let mut busy_diags_rl: [bool; 2 * N] = [false; 2 * N];
  count_queen_placements(
    0,
    &forbidden_cells,
    &mut busy_cols,
    &mut busy_diags_lr,
    &mut busy_diags_rl,
    &mut solutions,
  );
  io.writeln(solutions);
}

fn open_input() -> Reader {
  match std::env::args().nth(1) {
    Some(path) => {
      let f = File::open(&path).unwrap_or_else(|_| panic!("could not open input file: {}", path));
      Box::new(f)
    }
    None => Box::new(io::stdin()),
  }
}

struct Io<R: Read, W: Write> {
  input: BufReader<R>,
  output: BufWriter<W>,
}

impl<R: Read, W: Write> Io<R, W> {
  fn new(reader: R, writer: W) -> Self {
    Io {
      input: BufReader::new(reader),
      output: BufWriter::new(writer),
    }
  }

  fn next<T: FromStr>(&mut self) -> T {
    let token: Vec<u8> = self
      .input
      .by_ref()
      .bytes()
      .map(|b| b.expect("failed to read a byte from input"))
      .skip_while(|b| b.is_ascii_whitespace())
      .take_while(|b| !b.is_ascii_whitespace())
      .collect();
    std::str::from_utf8(&token)
      .unwrap()
      .parse()
      .ok()
      .unwrap_or_else(|| panic!("could not parse token as requested type"))
  }

  fn next_vec<T: FromStr>(&mut self, n: usize) -> Vec<T> {
    (0..n).map(|_| self.next()).collect()
  }

  fn next_line(&mut self) -> String {
    let mut s = String::new();
    self
      .input
      .read_line(&mut s)
      .expect("failed to read a line from input");
    s.trim_end().to_string()
  }

  fn next_grid(&mut self, n: usize) -> Vec<Vec<u8>> {
    (0..n).map(|_| self.next_line().into_bytes()).collect()
  }

  fn next_char(&mut self) -> Option<char> {
    self
      .input
      .by_ref()
      .bytes()
      .map(|b| b.expect("failed to read a byte from input"))
      .find(|b| !b.is_ascii_whitespace())
      .map(|b| b as char)
  }

  fn write<T: std::fmt::Display>(&mut self, val: T) {
    write!(self.output, "{}", val).unwrap();
  }

  fn write_sp<T: std::fmt::Display>(&mut self, val: T) {
    write!(self.output, "{} ", val).unwrap();
  }

  fn writeln<T: std::fmt::Display>(&mut self, val: T) {
    writeln!(self.output, "{}", val).unwrap();
  }

  fn flush(&mut self) {
    self.output.flush().unwrap();
  }
}

impl<R: Read, W: Write> Drop for Io<R, W> {
  fn drop(&mut self) {
    let _ = self.output.flush();
  }
}
