import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CursoService } from '../../services/curso.service';

@Component({
  selector: 'app-curso-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './curso-form.component.html',
  styleUrl: './curso-form.component.scss'
})
export class CursoFormComponent implements OnInit {

  form!: FormGroup;
  idCurso?: number;
  modoEdicao = false;

  constructor(
    private fb: FormBuilder,
    private cursoService: CursoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      nomeCurso: ['', [Validators.required, Validators.minLength(3)]]
    });

    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.modoEdicao = true;
      this.idCurso = Number(idParam);
      this.cursoService.findById(this.idCurso).subscribe({
        next: (curso) => this.form.patchValue(curso),
        error: (err) => console.error('Erro ao carregar curso', err)
      });
    }
  }

  salvar(): void {
    if (this.form.invalid) return;

    const curso = this.form.value;

    const operacao = this.modoEdicao
      ? this.cursoService.update(this.idCurso!, curso)
      : this.cursoService.insert(curso);

    operacao.subscribe({
      next: () => this.router.navigate(['/cursos']),
      error: (err) => console.error('Erro ao salvar curso', err)
    });
  }

  cancelar(): void {
    this.router.navigate(['/cursos']);
  }
}
