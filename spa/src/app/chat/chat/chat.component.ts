import {Component, OnInit} from '@angular/core';
import {WebSocketSubject} from 'rxjs/webSocket';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  private socket$: WebSocketSubject<Message|string>;
  messages: string[] = [];

  send;

  constructor() {
  }

  ngOnInit() {
  }

  connect(name: string) {
    if (name) {
      this.socket$ = new WebSocketSubject(`ws://localhost:8080/chat/connect/${name}`);
      this.socket$.subscribe((message: Message) => {
        this.messages = [
          ...this.messages,
          message.message
        ];
      });

      this.send = (msg: string) => this.socket$.next(msg);
    }
  }
}

interface Message {
  message: string;
}
