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

fn solve(io: &mut Io<Reader, Stdout>) {
  let mut heap: BinaryHeap<char> = BinaryHeap::new();
  while let Some(c) = io.next_char() {
    heap.push(c);
  }
  let mut sorted_characters = heap.into_sorted_vec();
  let mut char_indexes: Vec<usize> = (0..sorted_characters.len()).collect();

  let mut sorted_unique_permutations: BTreeSet<String> = BTreeSet::new();
  sorted_unique_permutations.insert(sorted_characters.iter().collect());
  while next_permutation(&mut char_indexes) {
    sorted_unique_permutations.insert(char_indexes.iter().map(|i| sorted_characters[*i]).collect());
  }
  io.writeln(&sorted_unique_permutations.len());
  for perm in &sorted_unique_permutations {
    io.writeln(perm);
  }
}

pub fn next_permutation(input: &mut Vec<usize>) -> bool {
  let last_ascending = match input.windows(2).rposition(|w| w[0] < w[1]) {
    Some(i) => i,
    None => {
      input.reverse();
      return false;
    }
  };
  let swap_with = input[last_ascending + 1..]
    .binary_search_by(|n| usize::cmp(&input[last_ascending], n).then(Ordering::Less))
    .unwrap_err();
  input.swap(last_ascending, last_ascending + swap_with);
  input[last_ascending + 1..].reverse();
  true
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
