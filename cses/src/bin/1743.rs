#![allow(unused)]
use std::cmp::{max, min, Reverse};
use std::collections::{BTreeMap, BTreeSet, BinaryHeap, HashMap, HashSet, VecDeque};
use std::fs::File;
use std::io::{self, BufRead, BufReader, BufWriter, Read, Stdout, Write};
use std::ops::{Index, IndexMut};
use std::str::FromStr;

fn main() {
  let reader = open_input();
  let writer = io::stdout();
  let mut io = Io::new(reader, writer);

  solve(&mut io);
  io.flush();
}

type Reader = Box<dyn Read>;

struct UppercaseCharCounter {
  per_char_count: [u32; 26],
}

impl Default for UppercaseCharCounter {
  fn default() -> UppercaseCharCounter {
    UppercaseCharCounter {
      per_char_count: [0; 26],
    }
  }
}

fn index_uppercase_char(c: char) -> usize {
  match c {
    'A'..='Z' => c as usize - 'A' as usize,
    _ => panic!("trying to count unsuported char {c}"),
  }
}

impl Index<char> for UppercaseCharCounter {
  type Output = u32;
  fn index(&self, c: char) -> &Self::Output {
    return &self.per_char_count[index_uppercase_char(c)];
  }
}

impl IndexMut<char> for UppercaseCharCounter {
  fn index_mut(&mut self, c: char) -> &mut Self::Output {
    return &mut self.per_char_count[index_uppercase_char(c)];
  }
}

impl UppercaseCharCounter {
  fn add_one(&mut self, c: char) {
    self[c] += 1;
  }
  fn total(&self) -> u32 {
    return self.per_char_count.iter().sum();
  }
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let mut char_counter: UppercaseCharCounter = UppercaseCharCounter::default();
  while let Some(c) = io.next_char() {
    char_counter.add_one(c);
  }
  let n = char_counter.total();
  for c in 'A'..='Z' {
    if char_counter[c] * 2 > n + 1 {
      return io.writeln("-1");
    }
  }
  let mut last = 'a';
  let mut result: Vec<char> = Vec::new();
  for i in 0..n {
    let mut placed = false;
    let remaining = n - i;
    for c in 'A'..='Z' {
      if char_counter[c] * 2 > remaining {
        result.push(c);
        char_counter[c] -= 1;
        placed = true;
        last = c;
        break;
      }
    }
    if !placed {
      for c in 'A'..='Z' {
        if char_counter[c] > 0 && c != last {
          result.push(c);
          char_counter[c] -= 1;
          last = c;
          break;
        }
      }
    }
  }
  io.writeln(result.iter().collect::<String>());
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
