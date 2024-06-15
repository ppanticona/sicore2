import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDetalleOrden } from '../detalle-orden.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../detalle-orden.test-samples';

import { DetalleOrdenService, RestDetalleOrden } from './detalle-orden.service';

const requireRestSample: RestDetalleOrden = {
  ...sampleWithRequiredData,
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('DetalleOrden Service', () => {
  let service: DetalleOrdenService;
  let httpMock: HttpTestingController;
  let expectedResult: IDetalleOrden | IDetalleOrden[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DetalleOrdenService);
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

    it('should create a DetalleOrden', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const detalleOrden = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(detalleOrden).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DetalleOrden', () => {
      const detalleOrden = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(detalleOrden).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DetalleOrden', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DetalleOrden', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a DetalleOrden', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDetalleOrdenToCollectionIfMissing', () => {
      it('should add a DetalleOrden to an empty array', () => {
        const detalleOrden: IDetalleOrden = sampleWithRequiredData;
        expectedResult = service.addDetalleOrdenToCollectionIfMissing([], detalleOrden);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(detalleOrden);
      });

      it('should not add a DetalleOrden to an array that contains it', () => {
        const detalleOrden: IDetalleOrden = sampleWithRequiredData;
        const detalleOrdenCollection: IDetalleOrden[] = [
          {
            ...detalleOrden,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDetalleOrdenToCollectionIfMissing(detalleOrdenCollection, detalleOrden);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DetalleOrden to an array that doesn't contain it", () => {
        const detalleOrden: IDetalleOrden = sampleWithRequiredData;
        const detalleOrdenCollection: IDetalleOrden[] = [sampleWithPartialData];
        expectedResult = service.addDetalleOrdenToCollectionIfMissing(detalleOrdenCollection, detalleOrden);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(detalleOrden);
      });

      it('should add only unique DetalleOrden to an array', () => {
        const detalleOrdenArray: IDetalleOrden[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const detalleOrdenCollection: IDetalleOrden[] = [sampleWithRequiredData];
        expectedResult = service.addDetalleOrdenToCollectionIfMissing(detalleOrdenCollection, ...detalleOrdenArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const detalleOrden: IDetalleOrden = sampleWithRequiredData;
        const detalleOrden2: IDetalleOrden = sampleWithPartialData;
        expectedResult = service.addDetalleOrdenToCollectionIfMissing([], detalleOrden, detalleOrden2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(detalleOrden);
        expect(expectedResult).toContain(detalleOrden2);
      });

      it('should accept null and undefined values', () => {
        const detalleOrden: IDetalleOrden = sampleWithRequiredData;
        expectedResult = service.addDetalleOrdenToCollectionIfMissing([], null, detalleOrden, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(detalleOrden);
      });

      it('should return initial array if no DetalleOrden is added', () => {
        const detalleOrdenCollection: IDetalleOrden[] = [sampleWithRequiredData];
        expectedResult = service.addDetalleOrdenToCollectionIfMissing(detalleOrdenCollection, undefined, null);
        expect(expectedResult).toEqual(detalleOrdenCollection);
      });
    });

    describe('compareDetalleOrden', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDetalleOrden(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareDetalleOrden(entity1, entity2);
        const compareResult2 = service.compareDetalleOrden(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareDetalleOrden(entity1, entity2);
        const compareResult2 = service.compareDetalleOrden(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareDetalleOrden(entity1, entity2);
        const compareResult2 = service.compareDetalleOrden(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
