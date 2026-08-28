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
  tree: Vec<i64>,
}

impl FenwickTree {
  fn new(size: usize) -> Self {
    FenwickTree {
      size,
      tree: vec![0; size + 1],
    }
  }
  fn update(&mut self, mut index: usize, value: i64) {
    while index <= self.size {
      self.tree[index] += value;
      index += index & index.wrapping_neg();
    }
  }
  fn get(&self, index: usize) -> i64 {
    let mut sum = 0;
    let mut i = index as i32;
    while i > 0 {
      sum += self.tree[i as usize];
      i -= i & i.wrapping_neg();
    }
    sum
  }
}

struct FenwickMultiset {
  ftree: FenwickTree,
  comp: Vec<i64>,
  size: i64,
}

impl FenwickMultiset {
  fn from(values: &Vec<i64>) -> Self {
    let mut comp: Vec<i64> = values.clone();
    comp.sort_unstable();
    comp.dedup();
    let mut ftree = FenwickTree::new(comp.len());
    let mut multiset = FenwickMultiset {
      ftree,
      comp,
      size: values.len() as i64,
    };
    for &e in values {
      multiset.insert(e);
    }
    multiset
  }
  fn insert(&mut self, value: i64) {
    let index = self.comp.partition_point(|&x| x < value) + 1;
    self.ftree.update(index, 1);
  }
  fn erase(&mut self, value: i64) {
    let index = self.comp.partition_point(|&x| x < value) + 1;
    self.ftree.update(index, -1);
    self.size -= 1;
  }
  fn count_le(&self, value: i64) -> i64 {
    let index = self.comp.partition_point(|&x| x <= value);
    self.ftree.get(index)
  }
  fn count_ge(&self, value: i64) -> i64 {
    let index = self.comp.partition_point(|&x| x < value);
    self.size - self.ftree.get(index)
  }
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let n: usize = io.next();
  let mut ranges: Vec<(i64, i64, usize)> = Vec::new();
  let mut rights: Vec<i64> = Vec::new();
  for i in 0..n {
    let l = io.next();
    let r = io.next();
    ranges.push((l, r, i));
    rights.push(r as i64);
  }
  // contains -> a <= c && b >= d
  ranges.sort_by(|&(a, b, _), &(c, d, _)| a.cmp(&c).then_with(|| d.cmp(&b)));
  let mut contains = FenwickMultiset::from(&rights);
  let mut res_contains = vec![0i64; n];
  // at range (l, r):
  // - all remaining ranges (a, b) have a >= l
  // - ranges with b <= r are contained in (l, r)
  for &(_, r, i) in &ranges {
    contains.erase(r);
    res_contains[i] = if contains.count_le(r) > 0 { 1 } else { 0 };
  }
  // is_contained -> a >= c && b <= d
  ranges.sort_by(|&(a, b, _), &(c, d, _)| c.cmp(&a).then_with(|| b.cmp(&d)));
  let mut is_contained = FenwickMultiset::from(&rights);
  let mut res_is_contained = vec![0i64; n];
  // at range (l, r):
  // - all remaining ranges (a, b) have a <= l
  // - ranges with b >= r are contained in (l, r)
  for &(_, r, i) in &ranges {
    is_contained.erase(r);
    res_is_contained[i] = if is_contained.count_ge(r) > 0 { 1 } else { 0 };
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
