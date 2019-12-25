package function.java.set_map_list;

class Teacher implements Comparable {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = 10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        if (!(arg0 instanceof Teacher)) {
            return 1;
        }
        Teacher t1 = (Teacher) arg0;

        return this.age - t1.getAge();
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
