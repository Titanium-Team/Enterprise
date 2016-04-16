package de.titanium.enterprise.Entity.Statistic;

public enum Statistics implements Statistic {

    HIGHEST_DEFENSE_SCORE {

        @Override
        public String getName() {
            return "Highest Defense Score";
        }

        @Override
        public boolean isMax() {
            return true;
        }

        @Override
        public boolean isAdd() {
            return false;
        }

    },
    DAMAGE_DEALT {

        @Override
        public String getName() {
            return "Damage Dealt";
        }

        @Override
        public boolean isMax() {
            return false;
        }

        @Override
        public boolean isAdd() {
            return true;
        }

    },
    DAMAGE_BLOCKED {

        @Override
        public String getName() {
            return "Damage Blocked";
        }

        @Override
        public boolean isMax() {
            return false;
        }

        @Override
        public boolean isAdd() {
            return true;
        }

    },
    LONGEST_KEY_STREAK {

        @Override
        public String getName() {
            return "Longest Key Streak";
        }

        @Override
        public boolean isMax() {
            return true;
        }

        @Override
        public boolean isAdd() {
            return false;
        }

    }

}
