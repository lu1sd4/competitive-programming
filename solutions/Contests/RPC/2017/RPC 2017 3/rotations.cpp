#include <bits/stdc++.h>
using namespace std;
int rightRotate(unsigned int n, unsigned int d) {
   return (n >> d) | (n << (32 - d));
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int t;
	unsigned int goal = (1<<32)-1, x, lmf = (1<<5)-1, can, n;
	cin >> t;
	while(t--) {
		can = 0;
		cin >> n;
		for(int i = 0; i < 32; i++)	{
			x = n & lmf;
			if(can & (1 << x)) {
				can = 0;
				break;
			} else 
				can |= (1 << x);
			n = rightRotate(n, 1);
		}
		if(can == goal)
			cout << "yes" << "\n";
		else
			cout << "no" << "\n";
	}
	return 0;
}

