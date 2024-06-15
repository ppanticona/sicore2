import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAutorizacion } from '../autorizacion.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../autorizacion.test-samples';

import { AutorizacionService, RestAutorizacion } from './autorizacion.service';

const requireRestSample: RestAutorizacion = {
  ...sampleWithRequiredData,
  fecIniVig: sampleWithRequiredData.fecIniVig?.toJSON(),
  fecFinVig: sampleWithRequiredData.fecFinVig?.toJSON(),
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('Autorizacion Service', () => {
  let service: AutorizacionService;
  let httpMock: HttpTestingController;
  let expectedResult: IAutorizacion | IAutorizacion[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AutorizacionService);
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

    it('should create a Autorizacion', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const autorizacion = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(autorizacion).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Autorizacion', () => {
      const autorizacion = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(autorizacion).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Autorizacion', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Autorizacion', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Autorizacion', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAutorizacionToCollectionIfMissing', () => {
      it('should add a Autorizacion to an empty array', () => {
        const autorizacion: IAutorizacion = sampleWithRequiredData;
        expectedResult = service.addAutorizacionToCollectionIfMissing([], autorizacion);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autorizacion);
      });

      it('should not add a Autorizacion to an array that contains it', () => {
        const autorizacion: IAutorizacion = sampleWithRequiredData;
        const autorizacionCollection: IAutorizacion[] = [
          {
            ...autorizacion,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAutorizacionToCollectionIfMissing(autorizacionCollection, autorizacion);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Autorizacion to an array that doesn't contain it", () => {
        const autorizacion: IAutorizacion = sampleWithRequiredData;
        const autorizacionCollection: IAutorizacion[] = [sampleWithPartialData];
        expectedResult = service.addAutorizacionToCollectionIfMissing(autorizacionCollection, autorizacion);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autorizacion);
      });

      it('should add only unique Autorizacion to an array', () => {
        const autorizacionArray: IAutorizacion[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const autorizacionCollection: IAutorizacion[] = [sampleWithRequiredData];
        expectedResult = service.addAutorizacionToCollectionIfMissing(autorizacionCollection, ...autorizacionArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const autorizacion: IAutorizacion = sampleWithRequiredData;
        const autorizacion2: IAutorizacion = sampleWithPartialData;
        expectedResult = service.addAutorizacionToCollectionIfMissing([], autorizacion, autorizacion2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(autorizacion);
        expect(expectedResult).toContain(autorizacion2);
      });

      it('should accept null and undefined values', () => {
        const autorizacion: IAutorizacion = sampleWithRequiredData;
        expectedResult = service.addAutorizacionToCollectionIfMissing([], null, autorizacion, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(autorizacion);
      });

      it('should return initial array if no Autorizacion is added', () => {
        const autorizacionCollection: IAutorizacion[] = [sampleWithRequiredData];
        expectedResult = service.addAutorizacionToCollectionIfMissing(autorizacionCollection, undefined, null);
        expect(expectedResult).toEqual(autorizacionCollection);
      });
    });

    describe('compareAutorizacion', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAutorizacion(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareAutorizacion(entity1, entity2);
        const compareResult2 = service.compareAutorizacion(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareAutorizacion(entity1, entity2);
        const compareResult2 = service.compareAutorizacion(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareAutorizacion(entity1, entity2);
        const compareResult2 = service.compareAutorizacion(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
