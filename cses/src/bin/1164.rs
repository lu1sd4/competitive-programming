#![allow(unused)]
use std::cmp::{max, min, Reverse};
use std::collections::{BTreeMap, BTreeSet, BinaryHeap, HashMap, HashSet, VecDeque};
use std::fs::File;
use std::io::{self, BufRead, BufReader, BufWriter, Read, Stdout, Write};
use std::ops::Bound::{Excluded, Unbounded};
use std::str::FromStr;
use std::thread::current;

fn main() {
  let reader = open_input();
  let writer = io::stdout();
  let mut io = Io::new(reader, writer);

  solve(&mut io);
  io.flush();
}

type Reader = Box<dyn Read>;

fn get_left_neighbor(
  treemap: &mut BTreeMap<usize, BTreeSet<usize>>,
  n: usize,
) -> Option<(&usize, &mut BTreeSet<usize>)> {
  treemap.range_mut((Unbounded, Excluded(n))).next_back()
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let n: usize = io.next();
  let mut customers: Vec<(usize, usize, usize)> =
    (0..n).map(|i| (io.next(), io.next(), i)).collect();
  customers.sort_unstable();
  let mut assignment = vec![0; n];
  let mut rooms: BTreeMap<usize, BTreeSet<usize>> = BTreeMap::new();
  let mut next_room = 1;
  for &(customer_start, customer_end, i) in &customers {
    match get_left_neighbor(&mut rooms, customer_start) {
      Some((&room_end, room_numbers)) => {
        let room = room_numbers.pop_first().unwrap();
        let empty = room_numbers.is_empty();

        assignment[i] = room;

        rooms.entry(customer_end).or_default().insert(room);

        if empty {
          rooms.remove_entry(&room_end);
        }
      }
      None => {
        assignment[i] = next_room;
        rooms.entry(customer_end).or_default().insert(next_room);
        next_room += 1;
      }
    }
  }
  io.writeln(next_room - 1);
  for a in assignment {
    io.write_sp(a);
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
