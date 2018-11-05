import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Employee} from '../models/employee';
import {HttpClient} from '@angular/common/http';
import {Department} from '../models/department';
import {Salary} from '../models/position';

@Component({
  selector: 'app-employees-dashbord',
  templateUrl: './employees-dashbord.component.html',
  styleUrls: ['./employees-dashbord.component.scss']
})
export class EmployeesDashbordComponent implements OnInit {

  employees$: Observable<Employee[]>;

  departments$: Observable<Department[]>;

  salaries$: Observable<Salary[]>;

  selected: Employee;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.getList();
    this.departments$ = this.http.get<Department[]>('http://localhost:8080/api/departments', {withCredentials: true});
    this.salaries$ = this.http.get<Salary[]>('http://localhost:8080/api/positions', {withCredentials: true});
  }

  getList = () => {
    this.employees$ = this.http.get<Employee[]>('http://localhost:8080/api/employees', {withCredentials: true});
    this.selected = null;
  };

  save = (employee: Employee): void => {
    this.http.post('http://localhost:8080/api/employees', employee, {withCredentials: true}).subscribe(this.getList);
  };

  update = (employee: Employee): void => {
    this.http.put('http://localhost:8080/api/employees/' + employee.id, employee, {withCredentials: true}).subscribe(this.getList);
  };

  delete = (employee: Employee): void => {
    this.http.delete('http://localhost:8080/api/employees/' + employee.id, {withCredentials: true}).subscribe(this.getList);
  }

}
