import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IProveedores } from '../proveedores.model';
import { ProveedoresService } from '../service/proveedores.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './proveedores-delete-dialog.component.html',
})
export class ProveedoresDeleteDialogComponent {
  proveedores?: IProveedores;

  constructor(protected proveedoresService: ProveedoresService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.proveedoresService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
