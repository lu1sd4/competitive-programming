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

fn find_min(a: i64, b: i64, i: usize, p: &Vec<i64>) -> i64 {
  if i == 0 {
    return i64::min((a + p[i] - b).abs(), (a - b + p[i]).abs());
  }
  return i64::min(
    find_min(a + p[i], b, i - 1, p), // put in a
    find_min(a, b + p[i], i - 1, p), // put in b
  );
}

fn find_min_iter(p: &Vec<i64>) -> i64 {
  let mut min_diff: i64 = i64::MAX;
  for mask in 0..(1 << p.len()) {
    let mut a: i64 = 0;
    let mut b: i64 = 0;
    for (i, p_i) in p.iter().enumerate() {
      if mask & (1 << i) == 0 {
        a += p_i;
      } else {
        b += p_i;
      }
    }
    min_diff = min_diff.min((a - b).abs());
  }
  min_diff
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let n: usize = io.next();
  let mut p: Vec<i64> = Vec::new();
  for _ in 0..n {
    p.push(io.next());
  }
  // let res = find_min(0, 0, n - 1, &p);
  io.writeln(find_min_iter(&p));
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

type Reader = Box<dyn Read>;

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
