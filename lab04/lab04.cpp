#include <iostream>
#include <cstdlib>
#include <string>
#include <cstring>
#include <sstream>
#include <vector>

using namespace std;

class Student {
	public:
		string imie;
		string nazwisko;
		string pesel;
		string indeks;
		string rok;
		string adres;
		
		void setImie(string imie ){
			this->imie = imie;
		}
		void setNazwisko(string nazwisko) {
			this->nazwisko = nazwisko;
		}
		void setPesel(string pesel) {
			this->pesel = pesel;
		}
		void setIndex(string indeks) {
			this->indeks = indeks;
		}
		void setRok(string rok) {
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
		string getPesel() {
			return this->pesel;
		}
		string getRok() {
			return this->rok;
		}
                string getIndex() {
			return this->indeks;
		}
		string getAdres() {
			return this->adres;
		}
		
};

bool checkYear(string x){
    if(stoi(x)>=1 && stoi(x)<=5){
        return true;
    }else{
        return false;
    }
}

bool checkPesel(string x){
    if(x.length() == 11){
        return true;
    }else{
        return false;
    }
}

string showAsRoman(string x){
    switch(stoi(x)){
        case 1:
            return "I";
            break;
        case 2:
            return "II";
            break;
        case 3:
            return "III";
            break;
        case 4:
            return "IV";
            break;
        case 5:
            return "V";
            break;
        default:
            break;
    }
}



void add(vector<Student> &arr){
    string imie;
    string nazwisko;
    string pesel;
    string indeks;
    string rok;
    string adres;
    Student student;
    
    cout << "DODAWANIE STUDENTA" << endl;
    cout << "Podaj imię: " << endl;
    cin >> imie;
    student.setImie(imie);
    
    cout << "Podaj nazwisko: " << endl;
    cin >> nazwisko;
    student.setNazwisko(nazwisko);
    
    cout << "Podaj pesel: " << endl;
    cin >> pesel;
    student.setPesel(pesel);
    
    cout << "Podaj numer indeksu: " << endl;
    cin >> indeks;
    student.setIndex(indeks);
    
    cout << "Podaj rok studiów: " << endl;
    cin >> rok;
    student.setRok(rok);
    
    cout << "Podaj adres zamieszkania: " << endl;
    cin >> adres;
    student.setAdres(adres);
    
    if(!checkYear(rok)){
        cout << "Nie dodano studenta! Podano błedny rok studiów" << endl;
    }else if(!checkPesel(pesel)){
        cout << "Nie dodano studenta! Podano błedny pesel" << endl;
    }else{
        arr.push_back(student);
        cout << "Dodano studenta!" << endl;
    }
    
    
}

void show(vector<Student> &arr){
    cout << "LISTA STUDENTÓW" << endl;
    for(int i=0; i<arr.size(); i++){
        cout << i+1 << ". ";
        Student student = arr.at(i);
        cout << student.getImie() << " ";
        cout << student.getNazwisko() << " ";
        cout << student.getPesel() << " ";
        cout << student.getIndex() << " ";
        cout << showAsRoman(student.getRok()) << " ";
        cout << student.getAdres() << endl;
    }
}

void menu(int &c, vector<Student> &s){
    cout << "wybierz: [1] pokaż listę studentów , [2] dodaj studenta , [3] wyjdź z programu" << endl;
    cin >> c;
    
    switch (c){
                case 1:
                    show(s);
                    break;
                case 2:
                    add(s);
                    break;
                case 3:
                    cout << "Kończę program" << endl;
                    break;
                default:
                    cout << "Nieznany wybór" << endl;
                    break;
                      
            }
}


int main() {
    
	vector<Student> students;
	int choice;
        
        while(choice!=3){
            menu(choice, students);
        }
        
	return 0;
}
