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

fn digit_length_at_position(target: u64) -> (u64, u64) {
  if target <= 9 {
    return (1, 0);
  }

  let mut digit_length = 2;
  let mut digits_before_block = 9;

  loop {
    let first_number = 10u64.pow(digit_length as u32 - 1);
    let number_count = 9 * first_number;
    let digits_in_block = number_count * digit_length;

    if target <= digits_before_block + digits_in_block {
      return (digit_length, digits_before_block);
    }

    digits_before_block += digits_in_block;
    digit_length += 1;
  }
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let q: u32 = io.next();
  for _ in (0..q) {
    let target: u64 = io.next();

    let (digit_length, digits_before_block) = digit_length_at_position(target);

    let position = target - digits_before_block - 1;

    let first_number = 10_u64.pow(digit_length as u32 - 1);
    let number_index = position / digit_length;
    let digit_index = position % digit_length;

    let number = first_number + number_index;

    let result = number
      .to_string()
      .chars()
      .nth(digit_index as usize)
      .unwrap();

    io.writeln(result);
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

#[cfg(test)]
mod test {
  use super::*;

  #[test]
  fn test_calculate_target_digit_count() {
    let in_out = vec![
      (1, 1),
      (9, 1),
      (10, 2),
      (189, 2),
      (190, 3),
      (2889, 3),
      (2890, 4),
      (38889, 4),
      (38890, 5),
      (488889, 5),
      (488890, 6),
      (1_000_000_000_000_000_000, 17),
    ];
    for (k, expected_digits) in in_out {
      let (actual, _) = digit_length_at_position(k);
      assert_eq!(actual, expected_digits, "target_digits({k}) = {actual}");
    }
  }
}
