from typing import List
from collections import deque


class RideSharingSystem:

    def __init__(self):
        self.drivers = deque()
        self.riders = deque()
        self.riders_set = set()

    def addRider(self, riderId: int) -> None:
        self.riders.append(riderId)
        self.riders_set.add(riderId)

    def addDriver(self, driverId: int) -> None:
        self.drivers.append(driverId)

    def matchDriverWithRider(self) -> List[int]:
        if self.drivers and self.riders:
            removed_rider = self.riders.popleft()
            self.riders_set.remove(removed_rider)
            return [self.drivers.popleft(), removed_rider]
        else:
            return [-1, -1]

    def cancelRider(self, riderId: int) -> None:
        if riderId in self.riders_set:
            self.riders_set.remove(riderId)
            self.riders.remove(riderId)
