import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ChatRoutingModule} from './chat-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';
import {ChatInputComponent} from './chat/chat-input/chat-input.component';
import {ChatComponent} from './chat/chat.component';
import {ChatMessageBoxComponent} from './chat/chat-message-box/chat-message-box.component';

@NgModule({
  imports: [
    CommonModule,
    ChatRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  declarations: [ChatInputComponent, ChatComponent, ChatMessageBoxComponent],
  exports: [ChatComponent]
})
export class ChatModule {
}
