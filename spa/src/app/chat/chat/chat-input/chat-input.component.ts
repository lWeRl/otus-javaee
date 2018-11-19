import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-chat-input',
  template: `<div>
    <input type="text" #input/>
    <button (click)="input.value ? send.emit(input.value) : false">Send</button>
  </div>`,
  styleUrls: ['./chat-input.component.scss']
})
export class ChatInputComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  @Output()
  send = new EventEmitter<string>();

}
