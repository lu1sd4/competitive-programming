#![allow(unused)]
use std::cmp::{max, min, Ordering, Reverse};
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
  tree: Vec<u32>,
}

impl FenwickTree {
  fn new(size: usize) -> Self {
    FenwickTree {
      size,
      tree: vec![0; size + 1],
    }
  }
  fn update(&mut self, mut index: usize, value: u32) {
    while index <= self.size {
      self.tree[index] += value;
      index += index & index.wrapping_neg();
    }
  }
  fn get(&self, mut index: usize) -> u32 {
    let mut sum = 0;
    while index != 0 {
      sum += self.tree[index as usize];
      index -= index & index.wrapping_neg();
    }
    sum
  }
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let n: usize = io.next();
  let mut ranges: Vec<(u32, u32, usize)> = Vec::new();
  let mut rights: Vec<u32> = Vec::with_capacity(n);
  for i in 0..n {
    let l = io.next();
    let r = io.next();
    ranges.push((l, r, i));
    rights.push(r);
  }
  rights.sort_unstable();
  rights.dedup();
  let mut ranges: Vec<(u32, usize, usize)> = ranges
    .into_iter()
    .map(|(l, r, idx)| {
        let comp_r = rights.partition_point(|&x| x < r) + 1;
        (l, comp_r, idx)
    })
    .collect();
  ranges.sort_by_key(|&(a, b, _)| (a, Reverse(b)));
  let mut contains = FenwickTree::new(rights.len());
  let mut res_contains = vec![0; n];
  for i in (0..n).rev() {
    let &(_, comp_r, range_idx) = &ranges[i];
    res_contains[range_idx] = contains.get(comp_r);
    contains.update(comp_r, 1);
  }
  let mut is_contained = FenwickTree::new(rights.len());
  let mut res_is_contained = vec![0; n];
  for i in 0..n {
    let &(_, comp_r, range_idx) = &ranges[i];
    res_is_contained[range_idx] = (i as u32) - is_contained.get(comp_r - 1);
    is_contained.update(comp_r, 1);
  }
  for c in res_contains {
    io.write_sp(c);
  }
  io.writeln("");
  for c in res_is_contained {
    io.write_sp(c);
  }
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
