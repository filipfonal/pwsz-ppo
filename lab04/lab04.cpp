/*
    autor: Filip Fonał 37919
 */ 
#include <iostream>
#include <cstdlib>
#include <string>
#include <cstring>
#include <sstream>
#include <vector>

using namespace std;

class Student {
    private:
            string imie;
            string nazwisko;
            string pesel;
            string indeks;
            string rok;
            string adres;

            string showAsRoman(string x){
                if(x.length()>=1){
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
                }else return "";
            }
                
        public:
        
            Student(string imie, string nazwisko, string pesel, string indeks){
                this->imie = imie;
                this->nazwisko = nazwisko;
                this->pesel = pesel;
                this->indeks = indeks;
            }

            void setYear(string rok) {
                    this->rok = rok;
            }

            void setAdress(string adres) {
                    this->adres = adres;
            }

            string getData(){
                return imie + " " + nazwisko + " " + pesel + " " +indeks+" "+showAsRoman(rok)+" "+adres;
            }

            bool checkYear(string x){
                if(x.length()>1){
                    if(stoi(x)>=1 && stoi(x)<=5){
                        return true;
                    }else{
                        return false;
                    }
                }else return true;    
            }

            bool checkPesel(string x){
                char monthLetter = x[2];
                char dayLetter = x[4];
                int month = monthLetter - '0';
                int day = dayLetter - '0';
                if(x.length() == 11 && month<=1 && day<=3){
                    return true;
                }else{
                    return false;
                }
            }
                
                
        
};

void add(vector<Student> &arr){
    string imie;
    string nazwisko;
    string pesel;
    string indeks;
    string rok;
    string adres;
    
    cout << "DODAWANIE STUDENTA" << endl;
    cout << "Podaj imię: " << endl;
    cin >> imie;
    cout << "Podaj nazwisko: " << endl;
    cin >> nazwisko;
    cout << "Podaj pesel: " << endl;
    cin >> pesel;
    cout << "Podaj numer indeksu: " << endl;
    cin >> indeks;
    cout << "Podaj rok studiów: " << endl;
    cin.ignore();
    getline(cin,rok);
    cout << "Podaj adres zamieszkania: " << endl;
    getline(cin,adres);
    
    Student student(imie, nazwisko, pesel, indeks);
    student.setYear(rok);
    student.setAdress(adres);
    
    if(!student.checkYear(rok)){
        cout << "Nie dodano studenta! Podano błedny rok studiów" << endl;
    }else if(!student.checkPesel(pesel)){
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
        cout << student.getData() << endl;
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
