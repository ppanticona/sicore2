import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEmpleados } from '../empleados.model';
import { EmpleadosService } from '../service/empleados.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './empleados-delete-dialog.component.html',
})
export class EmpleadosDeleteDialogComponent {
  empleados?: IEmpleados;

  constructor(protected empleadosService: EmpleadosService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.empleadosService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
