import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    private static final int REMOVE = 0;
    private static final int CREATE = 1;
    private static final int PILLAR = 0;
    private static final int BEAM = 1;

    public int[][] solution(int n, int[][] build_frame) {
        List<Structure> structures = new ArrayList<>();
        for (int[] command : build_frame) {
            handleCommand(command[0], command[1], command[2], command[3], structures);
        }

        int count = getDeletedCount(structures);

        Collections.sort(structures);
        int[][] answer = new int[structures.size()-count][3];
        int answerIndex = 0;
        for (Structure each : structures) {
            if (each.isDeleted) {
                continue;
            }
            answer[answerIndex++] = convertToAnswerFormat(each);
        }
        return answer;
    }

    private int getDeletedCount(List<Structure> structures) {
        int count = 0;
        for (Structure each : structures) {
            if (each.isDeleted) {
                count++;
            }
        }
        return count;
    }

    private void handleCommand(int x, int y, int a, int b, List<Structure> structures) {
        if (b == REMOVE) {
            int removedIndex = getMatchStructureIndex(x, y, a, structures);
            Structure removed = structures.get(removedIndex);
            removed.isDeleted = true;
            for (int index = 0; index < structures.size(); index++) {
                Structure each = structures.get(index);
                if (each.isDeleted) {
                    continue;
                }
                each.isDeleted = true;
                if (!canCreate(each.x, each.y, each.type, structures)) {
                    removed.isDeleted = false;
                    each.isDeleted = false;
                    return;
                }
                each.isDeleted = false;
            }
        }
        else if (b == CREATE) {
            if (!canCreate(x, y, a, structures)) {
                return;
            }
            for (Structure each : structures) {
                if (each.x == x && each.y == y && each.type == a) {
                    each.isDeleted = false;
                    return;
                }
            }
            structures.add(new Structure(x, y, a));
        }
        else {
            throw new IllegalArgumentException("Unknown command");
        }
    }

    private int getMatchStructureIndex(int x, int y, int a, List<Structure> structures) {
        for (int index = 0; index < structures.size(); index++) {
            Structure each = structures.get(index);
            if (each.x == x && each.y == y && each.type == a) {
                return index;
            }
        }
        throw new IllegalArgumentException("There is no match structure");
    }

    private boolean canCreate(int x, int y, int a, List<Structure> structures) {
        if (a == PILLAR) {
            return canCreatePillar(x, y, structures);
        }
        else if (a == BEAM) {
            return canCreateBeam(x, y, structures);
        }
        throw new IllegalArgumentException("There is no match structure");
    }

    private boolean canCreatePillar(int x, int y, List<Structure> structures) {
        if (y == 0) {
            return true;
        }
        for (Structure each : structures) {
            if (each.isDeleted) {
                continue;
            }
            if (each.type == PILLAR) {
                if (each.x == x && each.y+1 == y) {
                    return true;
                }
            }
            else if (each.type == BEAM) {
                if ((each.x == x && each.y == y) || (each.x+1 == x && each.y == y)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canCreateBeam(int x, int y, List<Structure> structures) {
        int touch = 0;
        for (Structure each : structures) {
            if (each.isDeleted) {
                continue;
            }
            if (each.type == PILLAR) {
                if ((each.x == x && each.y+1 == y) || (each.x == x+1 && each.y+1 == y)) {
                    return true;
                }
            }
            else if (each.type == BEAM) {
                if ((each.x+1 == x && each.y == y) || (each.x == x+1 && each.y == y)) {
                    touch++;
                }
                if (touch == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[] convertToAnswerFormat(Structure structure) {
        int[] result = new int[3];
        result[0] = structure.x;
        result[1] = structure.y;
        result[2] = structure.type;
        return result;
    }
}

class Structure implements Comparable<Structure> {

    int x;
    int y;
    int type;
    boolean isDeleted = false;

    Structure(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public int compareTo(Structure o) {
        if (x == o.x && y == o.y) {
            return Integer.compare(type, o.type);
        }
        if (x == o.x) {
            return Integer.compare(y, o.y);
        }
        return Integer.compare(x, o.x);
    }
}
