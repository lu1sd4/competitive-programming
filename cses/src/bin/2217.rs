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
  let n: usize = io.next();
  let m: usize = io.next();
  let mut v: Vec<usize> = (0..n).map(|_| io.next::<usize>() - 1).collect();
  let mut pos: Vec<usize> = vec![0; n];
  for (i, &x) in v.iter().enumerate() {
    pos[x] = i;
  }
  let mut adjacent_inversions = pos.windows(2).filter(|w| w[0] > w[1]).count() as i32;

  for _ in 0..m {
    let a: usize = io.next::<usize>() - 1;
    let b: usize = io.next::<usize>() - 1;
    let v_a = v[a];
    let v_b = v[b];

    let mut to_check = HashSet::new();
    if v_a > 0 {
      to_check.insert((v_a - 1, v_a));
    }
    if v_a < pos.len() - 1 {
      to_check.insert((v_a, v_a + 1));
    }
    if v_b > 0 {
      to_check.insert((v_b - 1, v_b));
    }
    if v_b < pos.len() - 1 {
      to_check.insert((v_b, v_b + 1));
    }

    let pre_inversions = to_check.iter().filter(|&&(i, j)| pos[i] > pos[j]).count() as i32;

    v.swap(a, b);
    pos.swap(v_a, v_b);

    let post_inversions = to_check.iter().filter(|&&(i, j)| pos[i] > pos[j]).count() as i32;

    adjacent_inversions += post_inversions - pre_inversions;

    io.writeln(adjacent_inversions + 1);
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
