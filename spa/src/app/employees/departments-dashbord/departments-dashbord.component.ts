import {Component, OnInit} from '@angular/core';
import {Employee} from '../models/employee';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Department} from '../models/department';
import {Salary} from '../models/position';

@Component({
  selector: 'app-departments-dashbord',
  templateUrl: './departments-dashbord.component.html',
  styleUrls: ['./departments-dashbord.component.scss']
})
export class DepartmentsDashbordComponent implements OnInit {

  selected: Department;
  private departments$: Observable<Department[]>;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.getList();
  }

  private getList = () => {
    this.departments$ = this.http.get<Department[]>('http://localhost:8080/api/departments', {withCredentials: true});
    this.selected = null;
  };

  save = (employee: Employee): void => {
    this.http.post('http://localhost:8080/api/departments', employee, {withCredentials: true}).subscribe(this.getList);
  };

  update = (employee: Employee): void => {
    this.http.put('http://localhost:8080/api/departments/' + employee.id, employee, {withCredentials: true}).subscribe(this.getList);
  };

  delete = (employee: Employee): void => {
    this.http.delete('http://localhost:8080/api/departments/' + employee.id, {withCredentials: true}).subscribe(this.getList);
  }


}
