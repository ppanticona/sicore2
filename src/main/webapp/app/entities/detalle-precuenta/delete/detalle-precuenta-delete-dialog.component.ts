import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDetallePrecuenta } from '../detalle-precuenta.model';
import { DetallePrecuentaService } from '../service/detalle-precuenta.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './detalle-precuenta-delete-dialog.component.html',
})
export class DetallePrecuentaDeleteDialogComponent {
  detallePrecuenta?: IDetallePrecuenta;

  constructor(protected detallePrecuentaService: DetallePrecuentaService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.detallePrecuentaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
