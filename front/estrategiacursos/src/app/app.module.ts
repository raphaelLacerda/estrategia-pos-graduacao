import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { PasswordModule } from 'primeng/password';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MegaMenuModule } from 'primeng/megamenu';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem, MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { MenuComponent } from './component/menu/menu.component';
import { LoginComponent } from './component/login/login.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CardModule } from 'primeng/card';
import { InputSwitchModule } from 'primeng/inputswitch';
import { ButtonModule } from 'primeng/button';
import { PanelModule } from 'primeng/panel';
import { TableModule } from 'primeng/table';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { EditorModule } from 'primeng/editor';
import { DropdownModule } from 'primeng/dropdown';
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { CheckboxModule } from 'primeng/checkbox';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { CalendarModule } from 'primeng/calendar';
import { MultiSelectModule } from 'primeng/multiselect';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { InputMaskModule } from 'primeng/inputmask';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { ListboxModule } from 'primeng/listbox';
import { HomeComponent } from './component/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { AlunoComponent } from './component/aluno/aluno.component';
import { ConcursoComponent } from './component/concurso/concurso.component';
import { DisciplinaComponent } from './component/disciplina/disciplina.component';
import { ProfessorComponent } from './component/professor/professor.component';
import { MatriculaComponent } from './component/matricula/matricula.component';
import { GradeComponent } from './component/grade/grade.component';
import { InterceptorModule } from './interceptor/interceptor.module';
import { InputNumberModule } from 'primeng/inputnumber';
import { DragDropModule } from 'primeng/dragdrop';
import {PickListModule} from 'primeng/picklist';
import { TagModule } from 'primeng/tag';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    LoginComponent,
    HomeComponent,
    AlunoComponent,
    ConcursoComponent,
    DisciplinaComponent,
    ProfessorComponent,
    MatriculaComponent,
    GradeComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    MegaMenuModule,
    MenubarModule,
    CommonModule,
    ConfirmDialogModule,
    FormsModule,
    PanelModule,
    CardModule,
    DialogModule,
    InputSwitchModule,
    InputTextareaModule,
    PanelModule,
    TableModule,
    DropdownModule,
    BrowserAnimationsModule,
    ButtonModule,
    EditorModule,
    ConfirmDialogModule,
    DataViewModule,
    InputTextModule,
    CheckboxModule,
    MatListModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    InputNumberModule,
    CalendarModule,
    MultiSelectModule,
    AutoCompleteModule,
    InputMaskModule,
    OverlayPanelModule,
    ListboxModule,
    RouterModule,
    ToastModule,
    PasswordModule,
    InterceptorModule,
    PickListModule,
    TagModule,
  ],
  providers: [MessageService],
  bootstrap: [AppComponent],
})
export class AppModule {}
