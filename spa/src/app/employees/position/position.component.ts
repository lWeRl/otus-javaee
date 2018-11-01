import {Component, Input, OnInit} from '@angular/core';
import {Salary} from '../models/position';

@Component({
  selector: 'app-position',
  templateUrl: './position.component.html',
  styleUrls: ['./position.component.scss']
})
export class PositionComponent implements OnInit {

  @Input()
  model: Salary = {};

  constructor() { }

  ngOnInit() {
  }

}
