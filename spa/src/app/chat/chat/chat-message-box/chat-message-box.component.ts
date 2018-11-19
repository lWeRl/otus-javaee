import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-chat-message-box',
  template: `
    <div *ngFor="let msg of messages">
      {{msg}}
    </div>
  `,
  styleUrls: ['./chat-message-box.component.scss']
})
export class ChatMessageBoxComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  @Input()
  messages: string[];

}
