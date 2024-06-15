import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IParametros } from '../parametros.model';
import { ParametrosService } from '../service/parametros.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './parametros-delete-dialog.component.html',
})
export class ParametrosDeleteDialogComponent {
  parametros?: IParametros;

  constructor(protected parametrosService: ParametrosService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.parametrosService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
