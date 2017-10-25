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
		string imie;
		string nazwisko;
		long pesel;
		int indeks;
		int rok;
		string adres;
		
		void setImie(string imie ){
			this->imie = imie;
		}
		void setNazwisko(string nazwisko) {
			this->nazwisko = nazwisko;
		}
		void setPesel(long pesel) {
			this->pesel = pesel;
		}
		void setIndex(int indeks) {
			this->indeks = indeks;
		}
		void setRok(int rok) {
			this->rok = rok;
		}
		void setAdres(string adres) {
			this->adres = adres;
		}
		
		
		
		
		string getImie() {
			return this->imie;
		}
		string getNazwisko() {
			return this->nazwisko;
		}
		long getPesel() {
			return this->pesel;
		}
		int getRok() {
			return this->rok;
		}
		string getAdres() {
			return this->adres;
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
