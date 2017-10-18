#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

class Point {
	public:
		int a;
		int b;
		int c;
		int d;
		
		Point() {
			//cout << "Point has been created." << endl;
		}
		
		Point(int a, int b, int c, int d) {
			this->a = a;
			this->b = b;
			this->c = c;
			this->d = d;
			//cout << "Point [" << this->x << ", " << this->y << "] has been created." << endl;
		}
		
		~Point() {
			//cout << "Point [" << this->x << ", " << this->y << "] has been deleted." << endl;
		}
		
};

class Circle {
	public:
		Point center;
		int radius;
		
		Circle(Point center, int radius) {
			this->center = center;
			this->radius = radius;
		}
		
		void getCoordinates() {
			cout << "x: " << this->center.x << endl << "y: " << this->center.y << endl;
		}
};

class Square{
	public:
		Point p;
		int d;
		
		Square(Point p, int d){
			this->p = p;
			this->d = d;
		}
};

int main() {
	srand(time(NULL));
	
	int inputA = 0, inputB = 0, inputC = 0, inputD = 0;
	int inputRadius = 5;
	
	Point p = Point(inputA, inputB, inputC, inputD);
	Circle c = Circle(p, inputRadius);
	
	c.center.movePoint(rand() % 10, rand() % 10);
	c.getCoordinates();
	
	return 0;
}
