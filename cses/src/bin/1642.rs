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

  solve_fast(&mut io);
  io.flush();
}

type Reader = Box<dyn Read>;

fn solve_fast(io: &mut Io<Reader, Stdout>) {
  let n: usize = io.next();
  if n < 4 {
    return io.writeln("IMPOSSIBLE");
  }
  let target: u64 = io.next();
  let mut v: Vec<u64> = (0..n).map(|_| io.next()).collect();
  let mut sums: HashMap<u64, (usize, usize)> = HashMap::new();
  for i in 0..n - 1 {
    for j in i + 1..n {
      let sum = v[i] + v[j];
      if sum > target {
        continue;
      }
      let rest = target - sum;
      if let Some(&(k, l)) = sums.get(&rest) {
        return io.writeln(format!("{} {} {} {}", i + 1, j + 1, k + 1, l + 1));
      }
    }
    for j in 0..i {
      sums.insert(&v[i] + v[j], (i, j));
    }
  }
  io.writeln("IMPOSSIBLE")
}

fn solve_slow(io: &mut Io<Reader, Stdout>) {
  let n: usize = io.next();
  if n < 4 {
    return io.writeln("IMPOSSIBLE");
  }
  let target: u64 = io.next();
  let mut v: Vec<(u64, usize)> = (0..n)
    .enumerate()
    .map(|(i, _)| (io.next(), i + 1))
    .collect();
  v.sort_unstable();
  for i in (0..n - 3) {
    let (a, a_i) = v[i];
    if a > target {
      continue;
    }
    let t_a = target - a;
    for j in (i + 1..n - 2) {
      let (b, b_i) = v[j];
      if b > t_a {
        continue;
      }
      let t_b = t_a - b;
      let mut left = j + 1;
      let mut right = n - 1;
      while left < right {
        if v[left].0 > t_b {
          left += 1;
          continue;
        }
        let looking_for = t_b - v[left].0;
        let n_right = v[right].0;
        if n_right > looking_for {
          right -= 1;
        } else if n_right < looking_for {
          left += 1;
        } else {
          return io.writeln(format!("{} {} {} {}", a_i, b_i, v[left].1, v[right].1));
        }
      }
    }
  }
  io.writeln("IMPOSSIBLE")
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
