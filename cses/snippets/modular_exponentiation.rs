fn pow_mod(x: i64, n: i64, m: i64) -> i64 {
  let mut res: i64 = 1;
  let mut exp: i64 = n;
  let mut acc: i64 = x;
  while exp > 0 {
    if exp % 2 != 0 {
      res = res * acc % m;
    }
    exp = exp / 2;
    acc = acc * acc % m;
  }
  return res;
}