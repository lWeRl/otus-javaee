import {Component, OnInit} from '@angular/core';
import {Salary} from '../models/position';
import {HttpClient} from '@angular/common/http';
import {Employee} from '../models/employee';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-potions-dashbord',
  templateUrl: './positions-dashbord.component.html',
  styleUrls: ['./positions-dashbord.component.scss']
})
export class PositionsDashbordComponent implements OnInit {

  selected: Salary;
  salaries$: Observable<Salary[]>;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.getList();
  }

  getList = () => {
    this.salaries$ = this.http.get<Salary[]>('http://localhost:8080/api/positions', {withCredentials: true});
    this.selected = null;
  };

  save = (employee: Employee): void => {
    this.http.post('http://localhost:8080/api/positions', employee, {withCredentials: true}).subscribe(this.getList);
  };

  update = (employee: Employee): void => {
    this.http.put('http://localhost:8080/api/positions/' + employee.id, employee, {withCredentials: true}).subscribe(this.getList);
  };

  delete = (employee: Employee): void => {
    this.http.delete('http://localhost:8080/api/positions/' + employee.id, {withCredentials: true}).subscribe(this.getList);
  };

}
