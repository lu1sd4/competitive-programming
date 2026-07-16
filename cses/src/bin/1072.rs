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

fn solve(io: &mut Io<Reader, Stdout>) {
  let up_to: usize = io.next();
  let mut t: Vec<usize> = vec![0,6,28,96,252];
  for n in 1..=up_to {
    let i = n - 1;
    if i >= t.len() {
      let inner = (n-3).pow(2);
      let outer = (n-1).pow(2) - inner;
      let new_layer = 2*n - 1;
      let new_layer_pairs = (new_layer * (new_layer - 1)) / 2 - 2;
      let res = t[i-1] + inner * new_layer + outer * new_layer - (outer - 6) * 2 - 6 - 4 + new_layer_pairs;
      t.push(res);
    }
    io.writeln(t[i]);
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
    self.input
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

struct Binomial<T> {
  table: Vec<Vec<T>>,
}

impl<T: Copy + Default + From<u8> + std::ops::Add<Output = T>> Binomial<T> {
  fn new(max_n: usize) -> Self {
    let mut table = vec![vec![T::default(); max_n + 1]; max_n + 1];
    for row in 0..=max_n {
      table[row][0] = T::from(1);
      for col in 1..=row {
        table[row][col] =
          table[row - 1][col - 1] + table[row - 1].get(col).copied().unwrap_or_default();
      }
    }
    Binomial { table }
  }

  fn choose(&self, n: usize, k: usize) -> T {
    if k > n {
      return T::default();
    }
    self.table[n][k]
  }
}

