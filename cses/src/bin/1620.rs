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

fn products_at_time(machines: &Vec<u64>, time: u64) -> u64 {
  let mut products = 0;
  machines.iter().map(|interval| time / interval).sum()
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let n_machines: usize = io.next();
  let n_products: u64 = io.next();
  let mut machines: Vec<u64> = (0..n_machines).map(|_| io.next()).collect();
  let mut min_time = 0;
  let mut max_time = machines.iter().max().unwrap() * n_products;
  while min_time < max_time {
    let time = min_time + (max_time - min_time) / 2;
    if products_at_time(&machines, time) >= n_products {
      max_time = time;
    } else {
      min_time = time + 1;
    }
  }
  io.writeln(min_time);
}

fn solve_simulate(io: &mut Io<Reader, Stdout>) {
  let n_machines: usize = io.next();
  let n_products: u64 = io.next();
  let mut machines: BTreeMap<u64, BTreeMap<u64, u64>> = BTreeMap::new();
  for _ in 0..n_machines {
    let machine_time: u64 = io.next();
    machines
      .entry(machine_time)
      .and_modify(|machine_specs| {
        *machine_specs.entry(machine_time).or_default() += 1;
      })
      .or_insert(BTreeMap::from([(machine_time, 1)]));
  }
  let mut products = 0;
  let mut time = 0;
  loop {
    let (current_time, current_specs) = machines.pop_first().unwrap();
    time = current_time;
    for (current_machine_interval, current_machine_copies) in &current_specs {
      products += current_machine_copies;
      if products >= n_products {
        return io.writeln(time);
      }
      let next_time = current_time + current_machine_interval;
      machines
        .entry(next_time)
        .and_modify(|next_specs| {
          next_specs.insert(*current_machine_interval, *current_machine_copies);
        })
        .or_insert(BTreeMap::from([(
          *current_machine_interval,
          *current_machine_copies,
        )]));
    }
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
