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
  let n_applicants: usize = io.next();
  let n_apartments: usize = io.next();
  let threshold: i32 = io.next();
  let mut applicants: Vec<i32> = (0..n_applicants).map(|_| io.next()).collect();
  let mut apartments: Vec<i32> = (0..n_apartments).map(|_| io.next()).collect();
  let mut taken: Vec<bool> = (0..n_apartments).map(|_| false).collect();
  applicants.sort_unstable();
  apartments.sort_unstable();

  let mut applicant_index: usize = 0;
  let mut apartment_index: usize = 0;
  let mut count: u32 = 0;

  while applicant_index < applicants.len() && apartment_index < apartments.len() {
    let applicant = applicants[applicant_index];
    let apartment = apartments[apartment_index];

    if apartment < applicant - threshold {
      apartment_index += 1;
    } else if apartment > applicant + threshold {
      applicant_index += 1;
    } else {
      apartment_index += 1;
      applicant_index += 1;
      count += 1;
    }
  }

  io.writeln(count);
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

#[cfg(test)]
mod test {
  use super::*;
  #[test]
  fn partition_point_test() {
    let arr = [1, 2, 3, 4, 5];
    let searching_for = 6;
    let found_at = arr.partition_point(|x| x < &searching_for);
    assert_eq!(found_at, 5);
  }
}
