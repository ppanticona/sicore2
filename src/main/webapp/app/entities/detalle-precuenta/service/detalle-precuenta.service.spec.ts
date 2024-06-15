import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDetallePrecuenta } from '../detalle-precuenta.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../detalle-precuenta.test-samples';

import { DetallePrecuentaService, RestDetallePrecuenta } from './detalle-precuenta.service';

const requireRestSample: RestDetallePrecuenta = {
  ...sampleWithRequiredData,
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('DetallePrecuenta Service', () => {
  let service: DetallePrecuentaService;
  let httpMock: HttpTestingController;
  let expectedResult: IDetallePrecuenta | IDetallePrecuenta[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DetallePrecuentaService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a DetallePrecuenta', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const detallePrecuenta = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(detallePrecuenta).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DetallePrecuenta', () => {
      const detallePrecuenta = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(detallePrecuenta).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DetallePrecuenta', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DetallePrecuenta', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a DetallePrecuenta', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDetallePrecuentaToCollectionIfMissing', () => {
      it('should add a DetallePrecuenta to an empty array', () => {
        const detallePrecuenta: IDetallePrecuenta = sampleWithRequiredData;
        expectedResult = service.addDetallePrecuentaToCollectionIfMissing([], detallePrecuenta);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(detallePrecuenta);
      });

      it('should not add a DetallePrecuenta to an array that contains it', () => {
        const detallePrecuenta: IDetallePrecuenta = sampleWithRequiredData;
        const detallePrecuentaCollection: IDetallePrecuenta[] = [
          {
            ...detallePrecuenta,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDetallePrecuentaToCollectionIfMissing(detallePrecuentaCollection, detallePrecuenta);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DetallePrecuenta to an array that doesn't contain it", () => {
        const detallePrecuenta: IDetallePrecuenta = sampleWithRequiredData;
        const detallePrecuentaCollection: IDetallePrecuenta[] = [sampleWithPartialData];
        expectedResult = service.addDetallePrecuentaToCollectionIfMissing(detallePrecuentaCollection, detallePrecuenta);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(detallePrecuenta);
      });

      it('should add only unique DetallePrecuenta to an array', () => {
        const detallePrecuentaArray: IDetallePrecuenta[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const detallePrecuentaCollection: IDetallePrecuenta[] = [sampleWithRequiredData];
        expectedResult = service.addDetallePrecuentaToCollectionIfMissing(detallePrecuentaCollection, ...detallePrecuentaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const detallePrecuenta: IDetallePrecuenta = sampleWithRequiredData;
        const detallePrecuenta2: IDetallePrecuenta = sampleWithPartialData;
        expectedResult = service.addDetallePrecuentaToCollectionIfMissing([], detallePrecuenta, detallePrecuenta2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(detallePrecuenta);
        expect(expectedResult).toContain(detallePrecuenta2);
      });

      it('should accept null and undefined values', () => {
        const detallePrecuenta: IDetallePrecuenta = sampleWithRequiredData;
        expectedResult = service.addDetallePrecuentaToCollectionIfMissing([], null, detallePrecuenta, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(detallePrecuenta);
      });

      it('should return initial array if no DetallePrecuenta is added', () => {
        const detallePrecuentaCollection: IDetallePrecuenta[] = [sampleWithRequiredData];
        expectedResult = service.addDetallePrecuentaToCollectionIfMissing(detallePrecuentaCollection, undefined, null);
        expect(expectedResult).toEqual(detallePrecuentaCollection);
      });
    });

    describe('compareDetallePrecuenta', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDetallePrecuenta(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareDetallePrecuenta(entity1, entity2);
        const compareResult2 = service.compareDetallePrecuenta(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareDetallePrecuenta(entity1, entity2);
        const compareResult2 = service.compareDetallePrecuenta(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareDetallePrecuenta(entity1, entity2);
        const compareResult2 = service.compareDetallePrecuenta(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
