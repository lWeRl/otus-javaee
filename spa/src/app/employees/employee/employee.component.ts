import {Component, Input, OnInit} from '@angular/core';
import {Employee} from '../models/employee';
import {Department} from '../models/department';
import {Salary} from '../models/position';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

  @Input()
  model: Employee;

  @Input()
  departments: Department[] = [];

  @Input()
  salaries: Salary[] = [];

  constructor() {
  }

  ngOnInit() {
  }

  byId = (left, right) => {
    if (left && right) {
      return left.id === right.id;
    }

    return false;
  }
}
