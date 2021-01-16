package transportSystem.train;


    public enum ComfortLevel{

        UNBEARABLE(0,9),
        UNCOMFORTABLE(10,25),
        TOLERABLE(26,49),
        COMFORTABLE(50,89),
        HEAVENLY(90,100);

        private int lowerBorder;
        private int upperBorder;

        ComfortLevel(int lowerBorder,int upperBorder){
            this.lowerBorder=lowerBorder;
            this.upperBorder = upperBorder;
        }

        public static ComfortLevel assignComfortLevel(int value){

            for(ComfortLevel level: ComfortLevel.values()){
                if((level.lowerBorder<=value)&&(level.upperBorder>=value)){
                    return  level;
                }
            }
            throw new IllegalArgumentException("Incorrect priority level");
        }


    }

