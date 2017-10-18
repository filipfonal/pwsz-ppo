#include <iostream>
#include <cstdlib>
#include <string>
#include <sstream>
#include <vector>
#include <time.h>

using namespace std;

#define STUDENTS_COUNT 10

class Student {
	public:
		string studentNo;
		string fname;
		string lname;
		int status;
		
		void setStudentNo(string studentNo) {
			this->studentNo = studentNo;
		}
		void setFname(string fname ){
			this->fname = fname;
		}
		void setLname(string lname) {
			this->lname = lname;
		}
		void setStatus(int status) {
			this->status = status;
		}
		
		
		
		string getStudentNo() {
			return this->studentNo;
		}
		string getFname() {
			return this->fname;
		}
		string getLname() {
			return this->lname;
		}
		int getStatus() {
			return this->status;
		}
};

string getRandomStudentNumber() {
	ostringstream ss;
	int randomNumber = rand() % 2000 + 37000;
	
	ss << randomNumber;
	
	return ss.str();
}

int getRandomStatus() {
	
	int randomNumber = rand() % 2;
	
	return randomNumber;
}

string getRandomStudentName(){
	string list[4] = {"Imie1", "Imie2", "Imie3", "Imie4"};
	return list[rand()%4];
}

string getRandomStudentLastName(){
	string list[4] = {"Nazwisko1", "Nazwisko2", "Nazwisko3", "Nazwisko4"};
	return list[rand()%4];
}

int main() {
	srand(time(NULL));
	vector<Student> students;
	
	for(int i = 0; i < STUDENTS_COUNT; i++) {
		Student student;
		
		student.setStudentNo(getRandomStudentNumber());
		student.setFname(getRandomStudentName());
		student.setLname(getRandomStudentLastName());
		student.setStatus(getRandomStatus());
		
		students.push_back(student);
	}
	
	cout  << "Students group have been filled." << endl << endl;
	
	
	for(int i = 0; i < students.size(); i++) {
		Student student = students.at(i);
		
		if(student.status==1){
		
		cout << student.getLname() << " ";
		cout << student.getFname() << " ";
		cout << "(" << student.getStudentNo() << ")" << endl;
		}
	}
	
	return 0;
}
