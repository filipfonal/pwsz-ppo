#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

class Point {
	public:
		int a;
		int b;
		
		Point() {
			//cout << "Point has been created." << endl;
		}
		
		Point(int a, int b) {
			this->a = a;
			this->b = b;
			//cout << "Point [" << this->x << ", " << this->y << "] has been created." << endl;
		}
		
		~Point() {
			//cout << "Point [" << this->x << ", " << this->y << "] has been deleted." << endl;
		}
		
};


class Square{
	public:
		Point a;
		Point b;
		Point c;
		Point d;
		int l;
		
		Square(Point a, int l){
			this->a = a;
			this->l = l;
		}
		
		void getSquare() {
			cout << "x1: " << this->a.a  << " " << "y1: " << this->a.b << endl;
			cout << "x2: " << this->a.a+l << " " << "y2: " << this->a.b << endl;
			cout << "x3: " << this->a.a << " " << "y3: " << this->a.b+l<< endl;
			cout << "x4: " << this->a.a+l << " " << "y4: " << this->a.b+l<< endl;
		}
};

int main() {
	srand(time(NULL));
	
	int inputA = 0, inputB = 0;
	int inputLength = 5;
	
	cout << "x1: ";
	 cin >> inputA;
	cout << "y1: ";
	 cin >> inputB;
	
	Point p = Point(inputA, inputB);
	Square s =Square(p, inputLength);
	
	s.getSquare();
	
	return 0;
}
