import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IMesas } from '../mesas.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../mesas.test-samples';

import { MesasService, RestMesas } from './mesas.service';

const requireRestSample: RestMesas = {
  ...sampleWithRequiredData,
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('Mesas Service', () => {
  let service: MesasService;
  let httpMock: HttpTestingController;
  let expectedResult: IMesas | IMesas[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(MesasService);
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

    it('should create a Mesas', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const mesas = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(mesas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Mesas', () => {
      const mesas = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(mesas).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Mesas', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Mesas', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Mesas', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addMesasToCollectionIfMissing', () => {
      it('should add a Mesas to an empty array', () => {
        const mesas: IMesas = sampleWithRequiredData;
        expectedResult = service.addMesasToCollectionIfMissing([], mesas);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(mesas);
      });

      it('should not add a Mesas to an array that contains it', () => {
        const mesas: IMesas = sampleWithRequiredData;
        const mesasCollection: IMesas[] = [
          {
            ...mesas,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addMesasToCollectionIfMissing(mesasCollection, mesas);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Mesas to an array that doesn't contain it", () => {
        const mesas: IMesas = sampleWithRequiredData;
        const mesasCollection: IMesas[] = [sampleWithPartialData];
        expectedResult = service.addMesasToCollectionIfMissing(mesasCollection, mesas);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(mesas);
      });

      it('should add only unique Mesas to an array', () => {
        const mesasArray: IMesas[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const mesasCollection: IMesas[] = [sampleWithRequiredData];
        expectedResult = service.addMesasToCollectionIfMissing(mesasCollection, ...mesasArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const mesas: IMesas = sampleWithRequiredData;
        const mesas2: IMesas = sampleWithPartialData;
        expectedResult = service.addMesasToCollectionIfMissing([], mesas, mesas2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(mesas);
        expect(expectedResult).toContain(mesas2);
      });

      it('should accept null and undefined values', () => {
        const mesas: IMesas = sampleWithRequiredData;
        expectedResult = service.addMesasToCollectionIfMissing([], null, mesas, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(mesas);
      });

      it('should return initial array if no Mesas is added', () => {
        const mesasCollection: IMesas[] = [sampleWithRequiredData];
        expectedResult = service.addMesasToCollectionIfMissing(mesasCollection, undefined, null);
        expect(expectedResult).toEqual(mesasCollection);
      });
    });

    describe('compareMesas', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareMesas(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareMesas(entity1, entity2);
        const compareResult2 = service.compareMesas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareMesas(entity1, entity2);
        const compareResult2 = service.compareMesas(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareMesas(entity1, entity2);
        const compareResult2 = service.compareMesas(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
