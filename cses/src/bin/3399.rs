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

const N: u32 = 100;

fn space_separated_rotated_slice(
  from: u32,
  to: u32,
  rotate_left: usize,
  vals: &[String],
) -> String {
  let slice = &vals[from as usize..to as usize];
  if slice.is_empty() {
    return String::new();
  }
  slice
    .iter()
    .cycle()
    .skip(rotate_left % slice.len())
    .take(slice.len())
    .map(String::as_str)
    .collect::<Vec<_>>()
    .join(" ")
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let t: u32 = io.next();
  let seq: Vec<String> = (1..=N).map(|i| i.to_string()).collect();

  for _ in 0..t {
    let n: u32 = io.next();
    let a: u32 = io.next();
    let b: u32 = io.next();
    let up_to = a + b;

    let moves = |rotate: usize| -> String {
      let head = space_separated_rotated_slice(0, up_to, rotate, &seq);
      let tail = space_separated_rotated_slice(up_to, n, 0, &seq);
      [head, tail]
        .into_iter()
        .filter(|s| !s.is_empty())
        .collect::<Vec<_>>()
        .join(" ")
    };

    if up_to == 0 {
      io.writeln("YES");
      io.writeln(moves(0));
      io.writeln(moves(0));
    } else if (up_to > 1 && a > 0 && b > 0 && up_to <= n) {
      io.writeln("YES");
      io.writeln(moves(0));
      io.writeln(moves(a as usize));
    } else {
      io.writeln("NO");
    }
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
