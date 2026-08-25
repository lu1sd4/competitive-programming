#![allow(unused)]
use std::cmp::{max, min, Reverse};
use std::collections::{BTreeMap, BTreeSet, BinaryHeap, HashMap, HashSet, VecDeque};
use std::fs::File;
use std::io::{self, BufRead, BufReader, BufWriter, Read, Stdout, Write};
use std::ops::Bound::{Excluded, Unbounded};
use std::str::FromStr;

fn main() {
  let reader = open_input();
  let writer = io::stdout();
  let mut io = Io::new(reader, writer);

  solve(&mut io);
  io.flush();
}

type Reader = Box<dyn Read>;

fn get_neighbors(treeset: &BTreeSet<i64>, n: i64) -> (i64, i64) {
  return (
    treeset
      .range((Unbounded, Excluded(n)))
      .next_back()
      .copied()
      .unwrap(),
    treeset
      .range((Excluded(n), Unbounded))
      .next()
      .copied()
      .unwrap(),
  );
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let x: i64 = io.next();
  let n = io.next();
  let mut lights: BTreeSet<i64> = BTreeSet::from([0, x]);
  let mut gaps: BTreeMap<i64, i64> = BTreeMap::from([(x, 0)]);
  for _ in 0..n {
    let p = io.next();
    let (left, right) = get_neighbors(&lights, p);
    lights.insert(p);

    let surrounding_gap = right - left;
    match gaps.get(&surrounding_gap) {
      Some(&count) => {
        if count > 1 {
          *gaps.get_mut(&surrounding_gap).unwrap() -= 1;
        } else {
          gaps.remove(&surrounding_gap);
        }
      }
      None => {
        panic!("surrounding gap not found?")
      }
    }

    let left_gap = p - left;
    let right_gap = right - p;
    gaps
      .entry(left_gap)
      .and_modify(|count| *count += 1)
      .or_insert(1);
    gaps
      .entry(right_gap)
      .and_modify(|count| *count += 1)
      .or_insert(1);

    io.write_sp(gaps.iter().next_back().unwrap().0);
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
