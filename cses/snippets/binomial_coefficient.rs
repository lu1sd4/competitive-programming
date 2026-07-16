struct Binomial<T> {
  table: Vec<Vec<T>>,
}

impl<T: Copy + Default + From<u8> + std::ops::Add<Output = T>> Binomial<T> {
  fn new(max_n: usize) -> Self {
    let mut table = vec![vec![T::default(); max_n + 1]; max_n + 1];
    for row in 0..=max_n {
      table[row][0] = T::from(1);
      for col in 1..=row {
        table[row][col] =
          table[row - 1][col - 1] + table[row - 1].get(col).copied().unwrap_or_default();
      }
    }
    Binomial { table }
  }

  fn choose(&self, n: usize, k: usize) -> T {
    if k > n {
      return T::default();
    }
    self.table[n][k]
  }
}
