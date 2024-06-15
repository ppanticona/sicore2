import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { RegComprasService } from '../service/reg-compras.service';

import { RegComprasComponent } from './reg-compras.component';

describe('RegCompras Management Component', () => {
  let comp: RegComprasComponent;
  let fixture: ComponentFixture<RegComprasComponent>;
  let service: RegComprasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'reg-compras', component: RegComprasComponent }]), HttpClientTestingModule],
      declarations: [RegComprasComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(RegComprasComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RegComprasComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(RegComprasService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 'ABC' }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.regCompras?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to regComprasService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getRegComprasIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getRegComprasIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
