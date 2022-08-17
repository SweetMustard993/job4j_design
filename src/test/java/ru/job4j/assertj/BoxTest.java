package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void getNumberVertexOfTetrahedron() {
        Box box = new Box(4, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(4);
    }

    @Test
    void getNumberVertexOfSphere() {
        Box box = new Box(0, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(0);
    }

    @Test
    void isExist() {
        Box box = new Box(4, 8);
        assertThat(box.isExist()).isEqualTo(true);
    }

    @Test
    void isNotExist() {
        Box box = new Box(9, 8);
        assertThat(box.isExist()).isEqualTo(false);
    }

    @Test
    void getAreaOfSphere() {
        Box box = new Box(0, 10);
        double expected = 1256.637;
        assertThat(Math.abs(box.getArea() - expected)).isLessThan(0.01);
    }

    @Test
    void getAreaOfTetrahedron() {
        Box box = new Box(6, 10);
        double expected = 600;
        assertThat(Math.abs(box.getArea() - expected)).isLessThan(0.01);
    }
}