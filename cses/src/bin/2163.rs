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

struct FenwickTree {
  size: usize,
  tree: Vec<i64>,
}

impl FenwickTree {
  fn new(size: usize) -> Self {
    let mut tree = FenwickTree {
      size,
      tree: vec![0; size + 1],
    };
    for i in 1..=size {
      tree.update(i, 1);
    }
    tree
  }
  fn update(&mut self, mut index: usize, value: i64) {
    while index <= self.size {
      self.tree[index] += value;
      index += index & index.wrapping_neg();
    }
  }
  fn find_kth(&self, target: i64) -> usize {
    let mut power: usize = 1;
    while power * 2 <= self.size {
      power *= 2;
    }

    let mut index = 0;
    let mut current_sum = 0;
    while power > 0 {
      let next = index + power;
      if next <= self.size {
        if current_sum + self.tree[next] < target {
          index = next;
          current_sum += self.tree[next];
        }
      }
      power /= 2;
    }

    index + 1
  }
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let n: usize = io.next();
  let k: i64 = io.next();
  let mut tree = FenwickTree::new(n);
  let mut alive: i64 = n as i64;
  let mut start_at: i64 = 1;
  for _ in 0..n {
    let move_count = k % alive;
    let to_remove = ((start_at + move_count - 1) % alive) + 1;
    let alive_index = tree.find_kth(to_remove);
    io.write_sp(alive_index);
    tree.update(alive_index, -1);
    alive -= 1;
    if alive > 0 {
      start_at = ((to_remove - 1) % alive) + 1;
    }
  }
  io.writeln("");
}

// intended solution uses O(logn) C++ set's find_by_order to retrieve the nth element in the set
// rust's btreeset doesn't have a find_by_order
fn solve_intended(io: &mut Io<Reader, Stdout>) {
  let n: u32 = io.next();
  let k: usize = 2;
  let mut kids: BTreeSet<u32> = (1..=n).collect();
  let mut pos: usize = 0;
  while kids.len() > 1 {
    pos = (pos + k - 1) % kids.len();
    let v = kids.iter().nth(pos).copied().unwrap();
    io.write_sp(v);
    kids.remove(&v);
  }
  io.write_sp(kids.first().unwrap());
  io.writeln("");
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
