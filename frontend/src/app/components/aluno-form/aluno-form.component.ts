import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { AlunoService } from '../../services/aluno.service';

@Component({
  selector: 'app-aluno-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatSelectModule],
  templateUrl: './aluno-form.component.html',
  styleUrl: './aluno-form.component.scss'
})
export class AlunoFormComponent implements OnInit {

  form!: FormGroup;
  idAluno?: number;
  modoEdicao = false;

  constructor(
    private fb: FormBuilder,
    private alunoService: AlunoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      nome: ['', [Validators.required]],
      sexo: ['', [Validators.required]],
      dt_nasc: ['', [Validators.required]]
    });

    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.modoEdicao = true;
      this.idAluno = Number(idParam);
      this.alunoService.getById(this.idAluno).subscribe({
        next: (aluno) => {
          const dtFormatada = aluno.dt_nasc
            ? new Date(aluno.dt_nasc).toISOString().substring(0, 10)
            : '';
          this.form.patchValue({ ...aluno, dt_nasc: dtFormatada });
        },
        error: (err) => console.error('Erro ao carregar aluno', err)
      });
    }
  }

  salvar(): void {
    if (this.form.invalid) return;

    const aluno = this.form.value;

    const operacao = this.modoEdicao
      ? this.alunoService.update(this.idAluno!, aluno)
      : this.alunoService.create(aluno);

    operacao.subscribe({
      next: () => this.router.navigate(['/alunos']),
      error: (err) => console.error('Erro ao salvar aluno', err)
    });
  }

  cancelar(): void {
    this.router.navigate(['/alunos']);
  }
}
