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

const N: usize = 7;

type Grid = [[bool; N]; N];
type Position = (usize, usize);
type Delta = (isize, isize);

#[derive(Clone, Copy)]
enum Move {
  Up,
  Down,
  Left,
  Right,
}

impl Move {
  fn from_char(c: char) -> Self {
    match c {
      'R' => Self::Right,
      'L' => Self::Left,
      'U' => Self::Up,
      'D' => Self::Down,
      _ => panic!("{} unknown move type", c),
    }
  }
  fn to_delta(&self) -> Delta {
    match self {
      Self::Up => (-1, 0),
      Self::Down => (1, 0),
      Self::Left => (0, -1),
      Self::Right => (0, 1),
    }
  }
  fn apply_to(self, (row, col): Position, grid: &Grid) -> Option<Position> {
    let position = match self {
      Self::Up if row > 0 => (row - 1, col),
      Self::Down if row + 1 < N => (row + 1, col),
      Self::Left if col > 0 => (row, col - 1),
      Self::Right if col + 1 < N => (row, col + 1),
      _ => return None,
    };
    (!grid[position.0][position.1]).then_some(position)
  }
}

fn is_split(
  left: Option<Position>,
  right: Option<Position>,
  up: Option<Position>,
  down: Option<Position>,
) -> bool {
  left.is_some() && right.is_some() && up.is_none() && down.is_none()
    || up.is_some() && down.is_some() && left.is_none() && right.is_none()
}

fn free_or_current(row: usize, col: usize, position: Position, grid: &Grid) -> bool {
  !grid[row][col] || (row, col) == position
}

fn degree_ok(position: Position, target: Position, grid: &Grid) -> bool {
  for row in 0..N {
    for col in 0..N {
      if grid[row][col] || (row, col) == target {
        continue;
      }
      let mut open = 0;
      if row > 0 && free_or_current(row - 1, col, position, grid) {
        open += 1;
      }
      if row + 1 < N && free_or_current(row + 1, col, position, grid) {
        open += 1;
      }
      if col > 0 && free_or_current(row, col - 1, position, grid) {
        open += 1;
      }
      if col + 1 < N && free_or_current(row, col + 1, position, grid) {
        open += 1;
      }
      if open < 2 {
        return false;
      }
    }
  }
  true
}

fn count_solutions(
  position: Position,
  current_step: usize,
  grid: &mut Grid,
  visited: usize,
  target: Position,
  path_description: &[Option<Move>],
) -> u32 {
  if position == target {
    return (visited == N * N) as u32;
  }

  if !degree_ok(position, target, grid) {
    return 0;
  }

  let [left, right, up, down] = [
    Move::Left.apply_to(position, grid),
    Move::Right.apply_to(position, grid),
    Move::Up.apply_to(position, grid),
    Move::Down.apply_to(position, grid),
  ];

  if is_split(left, right, up, down) {
    return 0;
  }

  let next_positions = match path_description[current_step] {
    Some(mv) => [mv.apply_to(position, grid), None, None, None],
    None => [left, right, up, down],
  };

  next_positions
    .iter()
    .flatten()
    .map(|next_pos| {
      grid[next_pos.0][next_pos.1] = true;
      let solutions = count_solutions(
        *next_pos,
        current_step + 1,
        grid,
        visited + 1,
        target,
        path_description,
      );
      grid[next_pos.0][next_pos.1] = false;
      solutions
    })
    .sum()
}

fn solve(io: &mut Io<Reader, Stdout>) {
  let mut path_description: Vec<Option<Move>> = Vec::new();
  while let Some(c) = io.next_char() {
    path_description.push(match c {
      '?' => None,
      _ => Some(Move::from_char(c)),
    });
  }
  let mut grid: Grid = [[false; N]; N];
  grid[0][0] = true;
  let n_solutions = count_solutions((0, 0), 0, &mut grid, 1, (N - 1, 0), &path_description);
  io.writeln(n_solutions);
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
