#include <iostream>
#include <stdlib.h>
#include <time.h>
#include <vector>
#include <math.h>

using namespace std;

class Point {
	public:
		float x;
		float y;
		
		Point();
		
		Point(float x, float y) {
			this->x = x;
			this->y = y;
		}
		
		void move(float x, float y) {
			this->x += x;
			this->y += y;
		}
};

class Figure {
	public:
		vector<Point> archive;
		
		void printCornersCoordinates() {
			for(int i = 0; i < corners.size(); i++) {
				Point corner = corners.at(i);
				
				cout << "[" << corner.x << ", " << corner.y << "]" << endl;
			}
		}
};


class Circle : public Figure {
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

class RegularFigure : public Figure {
	public:		
		RegularFigure(Point lowerLeftCorner, float height, float width) {
			Point upperLeftCorner = lowerLeftCorner;
			upperLeftCorner.move(0, height);
			
			Point upperRightCorner = lowerLeftCorner;
			upperRightCorner.move(width, height);
			
			Point lowerRightCorner = lowerLeftCorner;
			lowerRightCorner.move(width, 0);
			
			this->corners.push_back(lowerLeftCorner);
			this->corners.push_back(upperLeftCorner);
			this->corners.push_back(upperRightCorner);
			this->corners.push_back(lowerRightCorner);
		}
};



int main() {
	srand(time(NULL));
	
	vector<Figure> figures;
	
	Triangle t = Triangle(Point(0, 0), 3);
	cout << "Triangle: " << endl;
	t.printCornersCoordinates();
	
	Rectangle r = Rectangle(Point(0, 0), 3, 5);
	cout << endl << "Rectangle: " << endl;
	r.printCornersCoordinates();
	
	Square s = Square(Point(0, 0), 3);
	cout << endl << "Square: " << endl;
	s.printCornersCoordinates();
	
	return 0;
}

