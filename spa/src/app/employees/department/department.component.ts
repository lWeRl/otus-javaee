import {Component, Input, OnInit} from '@angular/core';
import {Department} from '../models/department';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.scss']
})
export class DepartmentComponent implements OnInit {

  @Input()
  model: Department = {};

  constructor() { }

  ngOnInit() {
  }

}
