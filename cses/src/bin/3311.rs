#![allow(unused)]
use std::cmp::{max, min, Reverse};
use std::collections::{BTreeMap, BTreeSet, BinaryHeap, HashMap, HashSet, VecDeque};
use std::fs::File;
use std::io::{self, BufRead, BufReader, BufWriter, Read, Stdout, Write};
use std::iter;
use std::str::FromStr;

fn main() {
  let reader = open_input();
  let writer = io::stdout();
  let mut io = Io::new(reader, writer);

  solve(&mut io);
  io.flush();
}

type Reader = Box<dyn Read>;

fn to_index(c: char) -> u32 {
  assert!(c.is_ascii_uppercase(), "unsupported char {c}");
  c as u32 - 'A' as u32
}

fn to_uppercase(i: u32) -> char {
  assert!(i < 26);
  char::from_u32(i + 'A' as u32).unwrap()
}

fn smallest_unused<I>(used: I) -> u32
where
  I: IntoIterator<Item = u32>,
{
  let mut mask: u32 = 0;
  for e in used {
    mask |= 1 << e;
  }
  mask.trailing_ones()
}

const NEIGHBOR_DELTAS: [(isize, isize); 2] = [(0, -1), (-1, 0)];

fn bounded_add(lhs: usize, rhs: isize, max: usize) -> Option<usize> {
  lhs.checked_add_signed(rhs).filter(|&result| result < max)
}

type GridPosition = (usize, usize);

fn neighbors(
  row: usize,
  col: usize,
  bound_rows: usize,
  bound_cols: usize,
) -> impl Iterator<Item = GridPosition> {
  NEIGHBOR_DELTAS.into_iter().filter_map(move |(di, dj)| {
    let i = bounded_add(row, di, bound_rows)?;
    let j = bounded_add(col, dj, bound_cols)?;
    Some((i, j))
  })
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let n: usize = io.next();
  let m: usize = io.next();
  let mut grid: Vec<Vec<u32>> = (0..n)
    .map(|_| (0..m).map(|_| to_index(io.next_char().unwrap())).collect())
    .collect();

  for i in 0..n {
    for j in 0..m {
      grid[i][j] = smallest_unused(
        iter::once((i, j))
          .chain(neighbors(i, j, n, m))
          .map(|(i, j)| grid[i][j]),
      );
    }
  }
  for row in grid {
    for cell in row {
      io.write(to_uppercase(cell));
    }
    io.writeln("");
  }
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

#[cfg(test)]
mod test {
  use super::*;
  #[test]
  fn mex_test() {
    let in_out: Vec<(&[u32], u32)> = vec![
      (&[1, 2, 3], 0),
      (&[3, 2, 0], 1),
      (&[0], 1),
      (&[2], 0),
      (&[2, 3], 0),
      (&[2, 3], 0),
      (&[1, 3, 2, 0], 4),
    ];
    for (used, expected) in in_out {
      assert_eq!(smallest_unused(used), expected);
    }
  }
  #[test]
  fn uppercase_index_test() {
    let in_out = vec![('A', 0), ('B', 1), ('C', 2), ('D', 3), ('E', 4)];
    for (letter, index) in in_out {
      assert_eq!(to_index(letter), index);
      assert_eq!(to_uppercase(index), letter);
    }
  }
}
